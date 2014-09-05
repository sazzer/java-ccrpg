define(["i18next"], (i18next) ->
  (validation) ->
    if (validation.message)
      validation.message
    else if (validation.errorCode)
      formatted = i18next.t("validation.message.#{validation.errorCode}", validation.values)
      formatted
    else
      "Unexpected validation response"
)