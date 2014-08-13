define(["ccrpg/base"], (Base) ->
  class MainPage extends Base
    @wrapperNode = "<div />"
    @markup = ""

    render: () ->
      container = @get("container")

      @outerNode = $(@constructor.wrapperNode)
      container.append(@outerNode)
      if (@constructor.markup)
        @outerNode.append($(@constructor.markup))

      return this

  return MainPage
)