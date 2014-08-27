define(["ccrpg/ui/panel"], (Panel) ->
  # The Help Screen is the screen that will be used to display the bulk of the help documentation
  class HelpScreen extends Panel
    # The actual markup to render
    @markup = """
    <span>This is the help screen. Help of all kind will appear here.</span>
    """
  return HelpScreen
)