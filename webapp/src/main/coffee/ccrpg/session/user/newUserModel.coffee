define(["ccrpg/model"], (Model) ->
  # Representation of the data to create a new user
  class NewUserModel extends Model
    # The model fields
    @fields = {
      # The email address
      email: {
        type: "string",
        required: true,
        minLength: 1
      },
      # The screen name
      screenName: {
        type: "string",
        required: true,
        minLength: 1
      },
      # The provider that the request came from
      provider: {
        type: "string",
        required: true,
        minLength: 1
      },
      # The ID that the provider uses for the user
      providerId: {
        type: "string",
        required: true,
        minLength: 1
      }
    }
  return NewUserModel
)