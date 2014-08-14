define(["ccrpg/ui/widget", "ccrpg/ui/headerBar"], (Widget, HeaderBar) ->
  # The main page of the application
  class MainPage extends Widget

    # Perform any widget specific rendering of the UI
    renderUi: () ->
      @headerBar = new HeaderBar({
        container: @contentBox
      }).render()

  return MainPage
)