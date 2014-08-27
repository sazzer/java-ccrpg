define(["ccrpg/ui/containerPanel"], (ContainerPanel) ->
  # A CardPanel is a ContainerPanel that contains a number of other panels, exactly one of which is visible at any given time
  class CardPanel extends ContainerPanel
    # Add a listener to the panel added signal so that we can then register listeners for the visibility of all the panels
    init: () ->
      super
      @panelAddedSignal.add (name, panel) =>
        panel.visibleChangedSignal.add (newVal) =>
          console.log("Panel #{name} visibility changed to #{newVal}")
          if (newVal)
            @_hideAllBut(name)

    # Show the panel that has the given name
    # @param name The name of the panel to show
    showPanel: (name) ->
      @getPanel(name).show()

    # Hide the panel that has the given name
    # @param name The name of the panel to hide
    hidePanel: (name) ->
      @getPanel(name).hide()

    # Hide all of the panels except for the one specified
    # @param name The name of the panel to keep visible
    _hideAllBut: (name) ->
      @forEach (panel, panelName) ->
        if (name != panelName)
          panel.hide()
  return CardPanel
)