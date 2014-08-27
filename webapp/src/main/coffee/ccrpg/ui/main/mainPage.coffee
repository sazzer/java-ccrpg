define(["ccrpg/ui/widget",
    "ccrpg/ui/cardPanel",
    "ccrpg/ui/main/headerBar",
    "ccrpg/ui/main/landingScreen",
    "ccrpg/ui/user/newUserScreen",
    "crossroads"],
(Widget, CardPanel, HeaderBar, LandingScreen, NewUserScreen, crossroads) ->
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
      @mainCardPanel.addPanel("newUserScreen", new NewUserScreen())
      @mainCardPanel.showPanel("landingScreen")
      
      crossroads.addRoute '/', () => @mainCardPanel.showPanel("landingScreen")

  return MainPage
)