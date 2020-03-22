# ImplicitIntents
In this section, create a new app with one Activity and three options for actions: open a web site, open a location on a map, and share a snippet of text. All of the text fields are editable (EditText), but contain default values.
In this task, implement the on-click handler method for the first button in the layout, Open Website. This action uses an implicit Intent to send the given URI to an Activity that can handle that implicit Intent (such as a web browser).

In this task, implement the on-click handler method for the second button in the UI, Open Location. This method is almost identical to the openWebsite() method. The difference is the use of a geo URI to indicate a map location. You can use a geo URI with latitude and longitude, or use a query string for a general location. In this example we've used the latter.

A share action is an easy way for users to share items in  app with social networks and other apps. Although we could build a share action in  app using an implicit Intent, Android provides the ShareCompat.IntentBuilder helper class to make implementing sharing easy. You can use ShareCompat.IntentBuilder to build an Intent and launch a chooser to let the user choose the destination app for sharing.

[![Implicit Intents](https://github.com/mk1995/ImplicitIntents/blob/master/ezgif.com-video-to-gif-min.gif "ImplicitIntent")](https://github.com/mk1995/ImplicitIntents/blob/master/ezgif.com-video-to-gif-min.gif "Implicit Intent") 
