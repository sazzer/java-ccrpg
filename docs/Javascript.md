Javascript requirements
=======================
* DOM Manipulation
    * Finding nodes
    * Rendering new nodes
    * Deleting nodes
    * Updating text content of nodes
    * Updating attributes
    * Updating CSS classes
* AJAX
    * GET/POST/PUT/DELETE
    * Ability to set global state. Possibly handled by a wrapper
* Event Handling
    * Ideally should be seamless between DOM and Custom events
* i18n
* URL Routing
    * Based on the Hash
* Promises?
    * Can easily be dropped in
* Widgets?
    * Can easily be implemented on top of the above

Framework or collection of libraries?

jQuery covers DOM Manipulation, AJAX, DOM Event Handling
js-signals covers custom event handling. Can use js-signals to expose DOM events as custom ones if desired
Hasher and Crossroads covers URL Routing
Promises are already available, and can be polyfilled if needed
i18next covers I18n support