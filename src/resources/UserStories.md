Story:
As an admin, at a top level customer organisation, I want to view a list of my direct suppliers.

Spec:
Given a user [U] is present in a top level customer organisation [O]
And a organisation [O] has a  list of direct suppliers [DirSup]
When [U] requests a list of direct suppliers
Then [DirSup] is returned

Test: [O] only has one direct supplier in [DirSup]
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
- Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS456] (ie.listOf("ZS456"))

Test: single direct supplier
- Somehow create scenario that : when domain tries to look up company for ZU122 it gets back ZC788
- Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455)
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS455]

Test: multiple direct suppliers
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back ZC789
- Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456, ZS111, ZS222)
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS456, ZS111, ZS222]

Test: no direct suppliers
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back ZC789
- Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes no direct suppliers
- When a user queries the domain for the direct suppliers
- Then assert that reply []

Test: no repeats in direct suppliers list
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back ZC789
- Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456, ZS111, ZS222)
- When a user queries the domain for the direct suppliers
- Then assert there are no repeats in the reply ([ZS456, ZS111, ZS222])






