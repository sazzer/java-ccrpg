define(["ccrpg/ui/containerPanel"], (ContainerPanel) ->
  # A CardPanel is a ContainerPanel that contains a number of other panels, exactly one of which is visible at any given time
  class CardPanel extends ContainerPanel
    # Show the panel that has the given name
    # @param name The name of the panel to show
    showPanel: (name) ->
    # Hide the panel that has the given name
    # @param name The name of the panel to hide
    hidePanel: (name) ->
  return CardPanel
)