define(["ccrpg/ui/widget", "ccrpg/ui/headerBar", "ccrpg/ui/cardPanel", "ccrpg/ui/landingScreen"], (Widget, HeaderBar, CardPanel, LandingScreen) ->
  # The main page of the application
  class MainPage extends Widget

    # Perform any widget specific rendering of the UI
    renderUi: () ->
      @headerBar = new HeaderBar({
        container: @contentBox
      }).render()

      @mainCardPanel = new CardPanel({
        container: @contentBox
      }).render()

      @mainCardPanel.addPanel("landingScreen", new LandingScreen())
  return MainPage
)