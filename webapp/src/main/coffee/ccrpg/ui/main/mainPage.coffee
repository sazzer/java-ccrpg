define(["ccrpg/ui/widget",
    "ccrpg/ui/cardPanel",
    "ccrpg/ui/main/headerBar",
    "ccrpg/ui/main/landingScreen",
    "ccrpg/ui/main/helpScreen",
    "ccrpg/ui/user/newUserScreen",
    "crossroads"],
(Widget, CardPanel, HeaderBar, LandingScreen, HelpScreen, NewUserScreen, crossroads) ->
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
      @mainCardPanel.addPanel("helpScreen", new HelpScreen())
      @mainCardPanel.addPanel("newUserScreen", new NewUserScreen())
      @mainCardPanel.showPanel("landingScreen")

      crossroads.addRoute '/', () => @mainCardPanel.showPanel("landingScreen")
      crossroads.addRoute '/help', () => @mainCardPanel.showPanel("helpScreen")

  return MainPage
)