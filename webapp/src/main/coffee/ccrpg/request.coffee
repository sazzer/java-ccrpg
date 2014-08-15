define(["bluebird", "ccrpg/base"], (Promise, Base) ->
  # Mechanism to make an AJAX Request
  # @param config The configuration for the request
  # @param config.url The URL to call
  # @param config.method The Method to use. Defaults to GET
  # @param config.data The data to provide to the method
  # @param config.expects The datatype to expect on return. Defaults to "json"
  class Request extends Base
    # Actually make the request
    # @return A promise that exposes the success or failure of the request
    go: () ->
      deferred = Promise.pending()

      completeHandler = (xhr, status) -> console.log("Ajax call complete: " + status)
      successHandler = (data, status, xhr) ->
        console.log("Ajax call successful: " + data)
        deferred.fulfill({
          data: data,
          status: status
        })
      errorHandler = (xhr, status, error) ->
        console.log("Ajax call error: " + error)
        deferred.reject({
          error: error,
          status: status
        })

      $.ajax({
        async: true,
        complete: completeHandler,
        context: this,
        data: @get("data"),
        dataType: @get("expects") || "json",
        error: errorHandler,
        success: successHandler,
        type: @get("method") || "GET",
        url: @get("url")
      })

      return deferred.promise
)