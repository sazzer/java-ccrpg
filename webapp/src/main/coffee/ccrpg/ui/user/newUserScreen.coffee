define(["ccrpg/ui/dialog"], (Dialog) ->
  # The New User screen is displayed when a user that we haven't seen before logs in
  class NewUserScreen extends Dialog
    # The actual markup to render
    @markup = """
    <span>This is the new user screen.</span>
    """
  return NewUserScreen
)