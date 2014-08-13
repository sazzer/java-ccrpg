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

      @outerNode = $(@constructor.wrapperNode)
      @outerNode.addClass(@constructor.name + "Widget")
      
      if (@constructor.markup)
        @outerNode.append($(@constructor.markup))

      container.append(@outerNode)
      return this

  return Widget
)