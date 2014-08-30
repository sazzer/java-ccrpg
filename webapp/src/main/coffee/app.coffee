requirejs.config({
  baseUrl: 'resources/js',
  paths: {

  }
})

require(["jquery"], () ->
    require(["jquery", "bootstrap", "main"])
)

