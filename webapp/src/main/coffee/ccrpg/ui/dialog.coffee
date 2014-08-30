define(["ccrpg/ui/panel"], (Panel) ->
  # A Dialog is a Panel that displays over the rest of the screen
  class Dialog extends Panel
    # Perform any widget specific rendering of the UI
    renderUi: () ->
        @hide()
  return Dialog
)