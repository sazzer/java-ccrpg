define(["ccrpg/ui/widget", "ccrpg/ui/cardPanel", "ccrpg/ui/main/headerBar", "ccrpg/ui/main/landingScreen"],
(Widget, CardPanel, HeaderBar, LandingScreen) ->
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
      @mainCardPanel.addPanel("landingScreen2", new LandingScreen())
      @mainCardPanel.showPanel("landingScreen")
  return MainPage
)