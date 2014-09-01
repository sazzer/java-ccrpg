define(["signals"], (signals) ->
  # Base class for data models
  class Model

    # Construct the model
    constructor: () ->
      @_data = {}
      @valueChanged = new signals.Signal()

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
      @_data[key] || fieldConfig.default

    # Set the value to the given key
    # @param key The key to set the value of
    # @param value The new value of the key
    set: (key, value) ->
      oldValue = @_data[key]
      @_data[key] = value
      if (oldValue != value)
        @valueChanged.dispatch(key, value, oldValue)

  return Model
)