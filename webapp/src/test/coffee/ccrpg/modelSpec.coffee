define(["ccrpg/model"], (Model) ->
  describe("A simple Model implementation", () ->
    class SimpleModel extends Model
      @fields = {
        name: {
          type: "string"
        },
        age: {
          type: "number"
        }
      }

    it("Has the two fields 'name' and 'age'", () ->
      instance = new SimpleModel()
      expect(instance.fields().length).toEqual(2)
      expect(instance.fields()).toContain("name")
      expect(instance.fields()).toContain("age")
    )
    describe("When the values haven't been set", () ->
      instance = new SimpleModel()
      it("Has no default for 'name'", () ->
        expect(instance.get("name")).not.toBeDefined()
      )
      it("Has no default for 'age'", () ->
        expect(instance.get("age")).not.toBeDefined()
      )
    )
    describe("When setting values", () ->
      instance = new SimpleModel()
      instance.set("name", "Graham")
      instance.set("age", 32)
      it("Has the correct value for 'name'", () ->
        expect(instance.get("name")).toEqual("Graham")
      )
      it("Has the correct value for 'age'", () ->
        expect(instance.get("age")).toEqual(32)
      )
    )
    describe("Signals a change", () ->
      it("sends an event when the value changes", () ->
        instance = new SimpleModel()
        expected = {}

        instance.set("name", "Fred")
        instance.valueChanged.addOnce((key, newVal, oldVal) ->
          expected.key = key
          expected.newVal = newVal
          expected.oldVal = oldVal
        )
        instance.set("name", "Graham")
        expect(expected.key).toEqual("name")
        expect(expected.oldVal).toEqual("Fred")
        expect(expected.newVal).toEqual("Graham")
      )
      it("sends no event when the value hasn't changed", () ->
        instance = new SimpleModel()
        expected = {}

        instance.set("name", "Graham")
        instance.valueChanged.addOnce((key, newVal, oldVal) ->
          expected.key = key
          expected.newVal = newVal
          expected.oldVal = oldVal
        )
        instance.set("name", "Graham")
        expect(expected.key).not.toBeDefined()
        expect(expected.oldVal).not.toBeDefined()
        expect(expected.newVal).not.toBeDefined()
      )
    )
  )
  describe("A model with default values", () ->
    class DefaultValuesModel extends Model
      @fields = {
        name: {
          type: "string",
          default: "Fred"
        },
        age: {
          type: "number",
          default: 99
        }
      }

    describe("When the values haven't been set", () ->
      instance = new DefaultValuesModel()
      it("Has a default for 'name'", () ->
        expect(instance.get("name")).toEqual("Fred")
      )
      it("Has a default for 'age'", () ->
        expect(instance.get("age")).toEqual(99)
      )
    )
    describe("When setting values", () ->
      instance = new DefaultValuesModel()
      instance.set("name", "Graham")
      instance.set("age", 32)
      it("Has the correct value for 'name'", () ->
        expect(instance.get("name")).toEqual("Graham")
      )
      it("Has the correct value for 'age'", () ->
        expect(instance.get("age")).toEqual(32)
      )
    )
  )
)
