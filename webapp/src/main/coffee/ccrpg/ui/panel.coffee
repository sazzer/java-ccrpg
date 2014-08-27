define(["signals", "ccrpg/ui/widget"], (signals, Widget) ->
  # A Panel is an extension of a Widget that has some extra logic for controlling large areas of the screen
  class Panel extends Widget

    init: () ->
      # Signal to trigger when the panel is shown or hidden
      @visibleChangedSignal = new signals.Signal()

    # Show the panel
    show: () ->
      wasVisible = @isVisible()
      @contentBox.show()
      @visibleChangedSignal.dispatch(true, wasVisible)

    # Hide the panel
    hide: () ->
      wasVisible = @isVisible()
      @contentBox.hide()
      @visibleChangedSignal.dispatch(false, wasVisible)

    # Set the visibility based on the provided value
    # @param visible True to make the panel visible. False to hide it
    setVisible: (visible) -> visible : @show() ? @hide()

    # Check if the panel is currently visible. This doesn't check if it's parent is visible, so a visible panel might
    # still not be seen on the screen
    # @return true if the panel is visible. False if it is hidden
    isVisible: () -> true
  return Panel
)