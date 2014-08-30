define(["ccrpg/ui/widget",
    "ccrpg/ui/cardPanel",
    "ccrpg/ui/main/headerBar",
    "ccrpg/ui/main/landingScreen",
    "ccrpg/ui/main/helpScreen",
    "ccrpg/ui/user/newUserScreen",
    "ccrpg/session/newUserSignal",
    "crossroads"],
(Widget, CardPanel, HeaderBar, LandingScreen, HelpScreen, NewUserScreen, newUserSignal, crossroads) ->
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
      @mainCardPanel.showPanel("landingScreen")

      @newUserDialog = new NewUserScreen().render()

      crossroads.addRoute '/', () => @mainCardPanel.showPanel("landingScreen")
      crossroads.addRoute '/help', () => @mainCardPanel.showPanel("helpScreen")
      newUserSignal.add (data) =>
#        @mainCardPanel.getPanel("newUserScreen").set("userId", data.id)
#        @mainCardPanel.getPanel("newUserScreen").set("provider", data.provider)
        @mainCardPanel.showPanel("newUserScreen")

  return MainPage
)