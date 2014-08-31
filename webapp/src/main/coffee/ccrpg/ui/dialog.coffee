define(["ccrpg/ui/widget"], (Widget) ->
  # A Dialog is a Panel that displays over the rest of the screen
  class Dialog extends Widget
    # The outermost element in which the widget is rendered
    @wrapperNode = """
    <div class="modal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">&times;</span>
              <span class="sr-only">{{s 'header.close'}}</span>
            </button>
            <h4 class="modal-title">{{s 'title'}}</h4>
          </div>
          <div class="modal-body">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">{{s 'button.cancel'}}</button>
            <button type="button" class="btn btn-primary">{{s 'button.ok'}}</button>
          </div>
        </div>
      </div>
    </div>
    """

    # Selector to find the content box in the wrapper
    @contentBoxSelector = ".modal-body"

    @signals = [
      "okClicked"
    ]

    # Perform any widget specific rendering of the UI
    renderUi: () ->
      @boundingBox.find(".modal-footer .btn-primary").on("click", (e) =>
        if (@_onOkClicked())
          @boundingBox.modal("hide")
          @signal("okClicked").dispatch(e)
      )

    # Display the dialog
    show: () ->
        @boundingBox.modal()

    # Handler for when the OK button is clicked on the dialog
    _onOkClicked: () -> true
  return Dialog
)