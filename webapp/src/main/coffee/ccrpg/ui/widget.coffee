define(["ccrpg/base", "handlebars", "i18next"], (Base, Handlebars, i18next) ->
  # Widget class for anything that gets rendered
  # Automatically handles some logic for rendering the widget
  class Widget extends Base
    # The outermost element in which the widget is rendered
    @wrapperNode = "<div />"
    # The actual markup to render
    @markup = ""

    # Render the widget
    render: () ->
      container = @get("container")

      @contentBox = $(@constructor.wrapperNode)
      @contentBox.addClass(@constructor.name + "Widget")

      if (@constructor.markup)
        self = this
        Handlebars.default.registerHelper("s", (string) ->
          return self.getString(string)
        )

        processedMarkup = Handlebars.default.compile(@constructor.markup)()
        @contentBox.append($(processedMarkup))

      @renderUi()
      container.append(@contentBox)
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