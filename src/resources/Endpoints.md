
User story 1:
As an admin at a top-level customer org
I want to view a list of my direct suppliers

### API docs:

GET /suppliers?type=direct

HTTP 200

Example Response:
```
["ZC123", "ZC234"]

```
If there are no suppliers, an empty array will be returned.
There are both directSuppliers and indirectSuppliers