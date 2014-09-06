define(["signals"], (signals) ->
  # Base class for data models
  class Model

    # Construct the model
    constructor: () ->
      @_data = {}
      @valueChanged = new signals.Signal()
      @fieldsReset = new signals.Signal()

    # Get the field config for the field with the given key
    # @param key The key of the field
    # @return the config for the field
    _fieldConfig: (key) ->
      config = @constructor.fields[key]
      unless (config)
        throw new Error("Requested field is unknown: #{key}")
      config

    # Get the field names that are defined on the model
    # @return the list of field names
    fields: () ->
      Object.keys(@constructor.fields)

    # Get the value from the given key
    # @param key The key to get the value of
    get: (key) ->
      fieldConfig = @_fieldConfig(key)
      result = @_data[key]
      if (result == undefined)
        result = fieldConfig.default
      result

    # Set the value to the given key
    # @param key The key to set the value of
    # @param value The new value of the key
    set: (key, value) ->
      oldValue = @_data[key]
      @_data[key] = value
      if (oldValue != value)
        @valueChanged.dispatch(key, value, oldValue)

      # Perform validation and return the result.
      @_validateField(key)


    # Clear the value of the given key
    # @param key The key to clear
    clear: (key) ->
      oldValue = @_data[key]
      delete @_data[key]
      @valueChanged.dispatch(key, @get(key), oldValue)

    # Reset the model to its default state
    reset: () ->
      for field in @fields()
        @clear field
      @fieldsReset.dispatch()

    # Return a JSON representation of the model
    # @return the model data, including any defaults filled out
    toJson: () ->
      result = {}
      for field in @fields()
        result[field] = @get(field)
      result

    # Perform validation on the entire model
    # @return object containing all of the validation errors
    validate: () ->
      result = {}
      for field in @fields()
        validation = @_validateField(field)
        if (validation && validation.length > 0)
          result[field] = validation
      result

    # Validate a single field
    # @param key The key of the field to validate
    # @return array of validation errors. Always returns an array, but may be empty if no errors occurred
    _validateField: (key) ->
      result = []
      fieldConfig = @_fieldConfig(key)
      fieldValue = @get(key)
      validations = [
        @_validateRequired,
        @_validateMinLength,
        @_validateMaxLength
      ]
      for v in validations
        valid = v(fieldConfig, key, fieldValue)
        if (valid)
          result.push(valid)
      result

    # Validate a required field has a value
    # @param fieldConfig the configuration of the field
    # @param fieldName the name of the field
    # @param fieldValue the value of the field
    # @return undefined if the field is valid. An object with the validation results if the field is invalid
    _validateRequired: (fieldConfig, fieldName, fieldValue) ->
      result = undefined
      if (fieldConfig.required && fieldValue == undefined)
        result = {
          errorCode: "required"
        }
      result

    # Validate a field has a required minimum legnth
    # @param fieldConfig the configuration of the field
    # @param fieldName the name of the field
    # @param fieldValue the value of the field
    # @return undefined if the field is valid. An object with the validation results if the field is invalid
    _validateMinLength: (fieldConfig, fieldName, fieldValue) ->
      result = undefined
      if (fieldValue != undefined && fieldConfig.type == "string" && fieldConfig.minLength && fieldValue.length < fieldConfig.minLength)
        result = {
          errorCode: "minLength",
          values: {
            minLength: fieldConfig.minLength,
            fieldLength: fieldValue.length
          }
        }
      result

    # Validate a field has a required maximum legnth
    # @param fieldConfig the configuration of the field
    # @param fieldName the name of the field
    # @param fieldValue the value of the field
    # @return undefined if the field is valid. An object with the validation results if the field is invalid
    _validateMaxLength: (fieldConfig, fieldName, fieldValue) ->
      result = undefined
      if (fieldValue != undefined && fieldConfig.type == "string" && fieldConfig.maxLength && fieldValue.length < fieldConfig.maxLength)
        result = {
          errorCode: "maxLength",
          values: {
            maxLength: fieldConfig.maxLength,
            fieldLength: fieldValue.length
          }
        }
      result
  return Model
)