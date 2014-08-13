define([], () ->
  ###
  # Base for all objects
  ###
  class Base
    ###
    # Construct the object, storing the initial data
    # @param _config The configuration to store
    ###
    constructor: (@_config) ->

    ###
    # Get the data with the given key
    # @param key The key of the data
    # @return the data
    ###
    get: (key) -> @_config[key]

    ###
    # Set the data with the given key
    # @param key The key of the data
    # @param value the new value of the data
    ###
    set: (key, value) -> @_config[key] = value
  return Base
)