define(['ccrpg/ui/mainPage', 'crossroads', 'hasher', "i18next"], (MainPage, crossroads, hasher, i18next) ->
  # Set up the Crossroads Routing
  crossroads.routed.add(console.log, console)

  crossroads.addRoute '/profile', () -> console.log("Going to the profile")

  # Set up Hasher to process the hash changes into Crossroads
  parseHash = (newHash, oldHash) -> crossroads.parse(newHash)

  hasher.initialized.add parseHash
  hasher.changed.add parseHash

  # Actually start things running
  i18next.init({
    resGetPath: 'locales/__ns__/__lng__'
    }, () ->
      mainPage = new MainPage({
        container: $("body")
      }).render()
      hasher.init()
  )

)