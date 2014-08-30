define(["ccrpg/request",
        "ccrpg/ui/widget",
        "ccrpg/session/loggedInSignal",
        "ccrpg/session/newUserSignal",
        "ccrpg/session/authenticationCallbackSignal"],
(Request, Widget, LoggedInSignal, NewUserSignal, AuthenticationCallbackSignal) ->
  # The header bar of the application
  class HeaderBar extends Widget

    # The outermost element in which the widget is rendered
    @wrapperNode = """<div class="loggedOut"/>"""

    # The actual markup to render
    @markup = """
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-reactive">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="sr-only">{{s 'toggleNavigation'}}</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#/">{{s 'pageTitle'}}</a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#/help">{{s 'help'}}</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#/profile" class="loggedIn">{{s 'profile'}}</a></li>
                    <li><a href="#/logout" class="loggedIn">{{s 'logout'}}</a></li>
                    <li class="dropdown loggedOut">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{s 'login'}}<span class="caret"></span></a>
                        <ul class="dropdown-menu login-menu" role="menu">
                        </ul>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
    """

    # Perform any widget specific rendering of the UI
    renderUi: () ->
      contentBox = @contentBox
      loginMenu = contentBox.find(".login-menu")

      LoggedInSignal.add (state) ->
        contentBox.toggleClass("loggedIn", state)
        contentBox.toggleClass("loggedOut", !state)

      loginMenu.on("click", "li a", () ->
        console.log("Starting a Login process")

        AuthenticationCallbackSignal.addOnce((data) ->
          console.log("Logged in from #{data.provider} with ID #{data.id}. New user: #{data.newUser}")
          if (data.newUser)
            NewUserSignal.dispatch(data)
        )
      )

      request = new Request({
        url: "/api/authentication/external"
      })
      request.go().then((result) =>
        console.log("Received the list of authentication providers to work with")
        for service in result.data
          serviceLabel = @getString("authentication.external.#{service}")
          markup = """<li><a href="api/authentication/external/#{service}" target="_blank">#{serviceLabel}</a></li>"""
          loginMenu.append($(markup))
      )


  return HeaderBar
)