define(["ccrpg/ui/dialog", "ccrpg/session/user/newUserModel"], (Dialog, NewUserModel) ->
  # The New User screen is displayed when a user that we haven't seen before logs in
  class NewUserScreen extends Dialog
    # The actual markup to render
    @markup = """
      <fieldset>
        <div class="form-group">
          <label for="screenName" class="control-label">{{s 'screenName.label'}}</label>
          <input type="text" class="form-control" name="screenName" data-binding="screenName" placeholder="{{s 'screenName.placeholder'}}" />
        </div>
        <div class="form-group">
          <label for="email" class="control-label">{{s 'email.label'}}</label>
          <input type="text" class="form-control" name="email" data-binding="email" placeholder="{{s 'email.placeholder'}}" />
        </div>
      </fieldset>
    """

    @modelClass = NewUserModel

    # Handler for when the OK button is clicked on the dialog
    _onOkClicked: () ->
      console.log("Ok clicked")
      true

  return NewUserScreen
)