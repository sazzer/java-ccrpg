define(["ccrpg/base"], (Base) ->
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
        @contentBox.append($(@constructor.markup))

      @renderUi()
      container.append(@contentBox)
      return this

    # Perform any widget specific rendering of the UI
    renderUi: () ->
  return Widget
)