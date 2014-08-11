define(['crossroads', 'hasher'], (crossroads, hasher) ->

  # Set up the Crossroads Routing
  crossroads.routed.add(console.log, console)

  crossroads.addRoute '/profile', () -> console.log("Going to the profile")

  # Set up Hasher to process the hash changes into Crossroads
  parseHash = (newHash, oldHash) -> crossroads.parse(newHash)

  hasher.initialized.add parseHash
  hasher.changed.add parseHash
  hasher.init()

)