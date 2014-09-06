define(["ccrpg/ui/validation"], (validationFormatter) ->
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
        if (control && control.length > 0)
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

      @model.fieldsReset.add () =>
        @_initialUpdate()

      @_initialUpdate()


    _initialUpdate: () ->
      # Set all of the view fields to the model initial values
      for field, control of @_viewFields
        control.val(@model.get(field))

      for field, control of @_viewFields
        @handleViewChange(control)


    # Handler for when a field on the view changes
    handleViewChange: (control) ->
      newVal = control.val()
      fieldName = control.attr("data-binding")
      validation = @model.set(fieldName, newVal)
      parent = control.parent(".form-group")
      alert = parent.find(".alert")
      alertList = alert.find("ul")
      unless (alertList && alertList.length > 0)
        alertList = alert.append("<ul></ul>")
      alertList.empty()
      if (validation && validation.length > 0)
        parent.addClass("has-error")
        for v in validation
          alertList.append("""<li>#{validationFormatter(v)}</li>""")
        alert.show()
      else
        parent.removeClass("has-error")
        alert.hide()

  return DataBinding
)