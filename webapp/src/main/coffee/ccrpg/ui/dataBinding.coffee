define([], () ->
  # Mechanism to automatically bind model and view together
  class DataBinding
    # Construct the data binder
    # @param model The model to store the data in
    # @param view The view to display the data
    constructor: (@model, @view) ->
      @_viewFields = {}

      # Find all of the View fields to match the model fields
      for field in @model.fields()
        control = @view.find("[data-binding='#{field}']")
        if (control)
          @_viewFields[field] = control

      # Wire up so that the view fields changing will change the model fields
      for field, control of @_viewFields
        control.on("change", (e) =>
          theControl = $(e.target)
          @handleViewChange(theControl)
        )

      # Wire up so that the model fields changing will change the view fields
      @model.valueChanged.add (key, newValue) =>
        viewField = @_viewFields[key]
        if (viewField)
          viewField.val(newValue)

      # Set all of the view fields to the model initial values
      for field, control of @_viewFields
        control.val(@model.get(field))

      # Assume for now that all of the fields are valid
      for field, control of @_viewFields
        parent = control.parent(".form-group")
        alert = parent.find(".alert")
        alert.hide()


    # Handler for when a field on the view changes
    handleViewChange: (control) ->
      newVal = control.val()
      fieldName = control.attr("data-binding")
      validation = @model.set(fieldName, newVal)
      parent = control.parent(".form-group")
      alert = parent.find(".alert")
      if (validation && validation.length > 0)
        parent.addClass("has-error")
        alert.empty()
        alert.append("An error occurred")
        alert.show()
      else
        parent.removeClass("has-error")
        alert.hide()

  return DataBinding
)