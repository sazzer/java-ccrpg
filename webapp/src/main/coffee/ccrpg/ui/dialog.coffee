define(["ccrpg/ui/widget"], (Widget) ->
  # A Dialog is a Panel that displays over the rest of the screen
  class Dialog extends Widget
    # The outermost element in which the widget is rendered
    @wrapperNode = """
    <div class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">&times;</span>
              <span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title">Modal title</h4>
          </div>
          <div class="modal-body">
            <p>One fine body&hellip;</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    """

    # Selector to find the content box in the wrapper
    @contentBoxSelector = ".modal-body"

    # Display the dialog
    show: () ->
        $(@boundingBox).modal()

  return Dialog
)