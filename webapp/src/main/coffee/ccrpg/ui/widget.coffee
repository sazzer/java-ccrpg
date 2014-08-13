define(["ccrpg/base"], (Base) ->
  class MainPage extends Base
    @wrapperNode = "div"
    @markup = ""

    render: () ->
      container = @get("container")

      @outerNode = container.append($(@constructor.wrapperNode))
      if (@constructor.markup)
        @outerNode.append($(@constructor.markup))

      return this

  return MainPage
)