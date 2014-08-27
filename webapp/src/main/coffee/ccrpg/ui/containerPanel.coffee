define(["ccrpg/ui/panel"], (Panel) ->
  # A ContainerPanel is a Panel that contains a number of other panels
  class ContainerPanel extends Panel
    # The outermost element in which the widget is rendered
    @wrapperNode = """<div class="container-reactive"/>"""

    # The collection of child panels
    _childPanels: {}

    # Add a new child panel to the container
    # This assumes that the child panel has not already been rendered somewhere, and will render it as a child of this
    # panel
    # @param name The name of the child panel
    # @param panel The child panel
    addPanel: (name, panel) ->
      @_childPanels[name] = panel
      panel.set("container", @contentBox)
      panel.render()

    # Get the child panel that has the given name
    # @param name the name of the child panel to get
    # @return the child
    getPanel: (name) -> @_childPanels[name]

    # Remove the child panel with the given name
    # @param name The name of the panel to remove
    removePanel: (name) -> delete @_childPanels[name]

  return ContainerPanel
)