
###  <font color="#33BBFF">✏️ User story 1:</font>
As an admin at a top-level customer org
I want to view a list of my direct suppliers

#### <font color="#33BBFF">API docs:</font>

GET /suppliers?type=direct

HTTP 200

Example Response:
```
["ZC123", "ZC234"]
```
If there are no direct suppliers, an empty list will be returned.

If the user does not exist, an empty list will be returned.

#### <font color="#33BBFF">Assumptions:</font>
The userId is stored as in the Request Header under "User-Id"