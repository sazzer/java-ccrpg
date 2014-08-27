define(["ccrpg/ui/panel"], (Panel) ->
  # The Landing Screen is the screen that is visible by default if nothing else is shown
  class LandingScreen extends Panel
    # The actual markup to render
    @markup = """
    <span>This is the loading screen. All kinds of interesting stuff will go here.</span>
    """
  return LandingScreen
)