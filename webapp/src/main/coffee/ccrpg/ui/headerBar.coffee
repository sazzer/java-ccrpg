define(["ccrpg/ui/widget"], (Widget) ->
  # The header bar of the application
  class HeaderBar extends Widget
    @markup = """
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#/">CCG RPG</a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#/help">Help</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#/profile">Profile</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#/login/external/facebook">Facebook</a></li>
                            <li><a href="#/login/external/google">Google+</a></li>
                            <li><a href="#/login/external/twitter">Twitter</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
    """

  return HeaderBar
)