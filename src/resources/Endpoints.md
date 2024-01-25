
###  ✏️ User story 1:
As an admin at a top-level customer org
I want to view a list of my direct suppliers

#### - API docs:

GET /suppliers?type=direct

HTTP 200

Example Response:
```
["ZC123", "ZC234"]
```
If there are no direct suppliers, an empty list will be returned.

If the user does not exist, an empty list will be returned.

#### - Assumptions:
The userId is stored as in the Request Header under "User-Id"