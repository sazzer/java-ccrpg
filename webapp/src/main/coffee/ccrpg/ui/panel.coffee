define(["ccrpg/ui/widget"], (Widget) ->
  # A Panel is an extension of a Widget that has some extra logic for controlling large areas of the screen
  class Panel extends Widget

    # The signals that this class can raise
    @signals = ["visibleChanged"]

    # Show the panel
    show: () ->
      wasVisible = @isVisible()
      @boundingBox.show()
      @signal("visibleChanged").dispatch(true, wasVisible)

    # Hide the panel
    hide: () ->
      wasVisible = @isVisible()
      @boundingBox.hide()
      @signal("visibleChanged").dispatch(false, wasVisible)

    # Set the visibility based on the provided value
    # @param visible True to make the panel visible. False to hide it
    setVisible: (visible) -> visible : @show() ? @hide()

    # Check if the panel is currently visible. This doesn't check if it's parent is visible, so a visible panel might
    # still not be seen on the screen
    # @return true if the panel is visible. False if it is hidden
    isVisible: () -> true
  return Panel
)