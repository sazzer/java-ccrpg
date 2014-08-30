define(["ccrpg/base", "handlebars", "i18next"], (Base, Handlebars, i18next) ->
  # Widget class for anything that gets rendered
  # Automatically handles some logic for rendering the widget
  class Widget extends Base
    # The outermost element in which the widget is rendered
    @wrapperNode = "<div />"
    # The actual markup to render
    @markup = ""

    # The attributes common to a widget
    @attrs = {
        "container": {defaultValue: $("body")}
    }

    # Render the widget
    render: () ->
      container = @get("container")

      Handlebars.default.registerHelper("s", (string) =>
        return @getString(string)
      )

      processedWrapper = Handlebars.default.compile(@constructor.wrapperNode)()
      @boundingBox = $(processedWrapper)
      @boundingBox.addClass(@constructor.name + "Widget")

      if (@constructor.contentBoxSelector)
        @contentBox = @boundingBox.find(@constructor.contentBoxSelector)
      else
        @contentBox = @boundingBox

      if (@constructor.markup)
        processedMarkup = Handlebars.default.compile(@constructor.markup)()
        @contentBox.append($(processedMarkup))

      @renderUi()
      container.append(@boundingBox)
      return this

    # Get an Internationalized string for this widget
    # @param string The string key to get
    # @return the internationalized string
    getString: (string) ->
      return i18next.t(@constructor.name + "." + string)

    # Perform any widget specific rendering of the UI
    renderUi: () ->
  return Widget
)