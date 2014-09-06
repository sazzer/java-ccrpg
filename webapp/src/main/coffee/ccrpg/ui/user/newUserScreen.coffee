define(["ccrpg/ui/dialog",
        "ccrpg/request",
        "ccrpg/session/user/newUserModel"], (Dialog, Request, NewUserModel) ->
  # The New User screen is displayed when a user that we haven't seen before logs in
  class NewUserScreen extends Dialog
    # The actual markup to render
    @markup = """
      <fieldset>
        <div class="alert alert-danger newUserErrors"></div>
        <div class="form-group">
          <label for="screenName" class="control-label">{{s 'screenName.label'}}</label>
          <input type="text" class="form-control" name="screenName" data-binding="screenName" placeholder="{{s 'screenName.placeholder'}}" />
          <div class="alert alert-danger"></div>
        </div>
        <div class="form-group">
          <label for="email" class="control-label">{{s 'email.label'}}</label>
          <input type="text" class="form-control" name="email" data-binding="email" placeholder="{{s 'email.placeholder'}}" />
          <div class="alert alert-danger"></div>
        </div>
      </fieldset>
    """

    @nodes = {
      "screenName": "input[name='screenName']",
      "email": "input[name='email']",
      "inputs": "input",
      "errors": ".newUserErrors"
    }

    @modelClass = NewUserModel

    # Set the External Authentication Provider and ID that we are registering with
    # @param provider The External Provider
    # @param id The External Provider ID
    setExternalId: (provider, id) ->
      @getModel().set("provider", provider)
      @getModel().set("providerId", id)

    # Handler for when the dialog is displayed
    _onShown: () ->
      @getModel().reset()
      @node("errors").empty().hide()
      @node("screenName").focus()

    # Handler for when the OK button is clicked on the dialog
    _onOkClicked: () ->
      newUserErrors = @node("errors")
      newUserErrors.empty().hide()

      validationResult = @getModel().validate()
      if (validationResult && Object.keys(validationResult).length > 0)
        console.log("Form is invalid")
        # Set the focus on the first input that has a validation error
        @node("inputs").each (index, input) ->
          bindingName = input.getAttribute("data-binding")
          if (validationResult[bindingName])
            input.focus()
            false
          else
            true
      else
        request = new Request({
            url: "/api/user",
            method: "POST",
            data: @getModel().toJson()
        })
        request.go().then((result) =>
          @hide()
        ).catch((err) =>
          if (err.statusCode == 400)
            errorMsg = err.response
          else
            errorMsg = "An internal error occurred"
          console.log("Error creating the user: #{err.error}")
          newUserErrors.text("Error creating user: #{errorMsg}").show()
        )
        console.log("Form is valid")
      false

  return NewUserScreen
)