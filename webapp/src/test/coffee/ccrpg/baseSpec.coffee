define(["ccrpg/base"], (Base) ->
    describe("the Base class", () ->
        describe("can be instantiated on its own", () ->
            b = new Base()
            it("should be defined", () ->
                expect(b).toBeDefined()
            )
        )
        describe("supports attributes with default values", () ->
            class Derived extends Base
                @attrs = {
                    "hello": {defaultValue: "world"},
                    "answer": {defaultValue: 42}
                }

            d = new Derived()
            it("should have a value of 'world' for the attribute 'hello'", () ->
                expect(d.get("hello")).toBe("world")
            )
            it("should have a value of '42' for the attribute 'answer'", () ->
                expect(d.get("answer")).toBe(42)
            )
        )
    )
)
