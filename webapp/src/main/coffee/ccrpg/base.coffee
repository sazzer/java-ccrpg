define(["signals"], (signals) ->
  # Base for all objects
  class Base
    # Construct the object, storing the initial data
    # @param config The configuration to store
    constructor: (config) ->
      @_config = {}
      @_signals = {}

      # Build the signals from the entire hierarchy of classes
      start = @constructor
      while (start)
        if (start.signals)
          for s in start.signals
            unless (@_signals[s])
              @_signals[s] = new signals.Signal()

        if (start.attrs)
          for a, v of start.attrs
            unless (@_config[a])
              @_config[a] = {
                attr: v
                value: undefined
              }

        for c, v of config
          @set(c, v)

        if (start.__super__)
          start = start.__super__.constructor
        else
          start = undefined

      @init()

    # Initialize the object
    init: () ->

    # Get the data with the given key
    # @param key The key of the data
    # @return the data
    get: (key) ->
      attr = @_config[key]
      unless (attr)
        throw new Error("Attribute #{key} not defined")
      return attr.value || attr.attr.defaultValue

    # Set the data with the given key
    # @param key The key of the data
    # @param value the new value of the data
    set: (key, value) ->
      attr = @_config[key]
      unless (attr)
        throw new Error("Attribute #{key} not defined")
      attr.value = value

    # Get the signal with the given key
    # @param key The key of the signal
    # @return the signal
    signal: (key) -> @_signals[key]
  return Base
)