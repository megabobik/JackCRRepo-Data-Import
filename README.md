# Kaninhop

Library for creating In-Memory JCR Repository (library which use Jackrabbit Repo underneath)

Features:
- Creates complete in-memory Jackrabbit JCR Repo (with help of nl.openweb.jcr.in-memory-jcr), which is fully functioning JCR report which can be used for testing queries, testing structure etc.

[![Build Status](https://dev.azure.com/vagabundus/Kaninhop/_apis/build/status/Kaninhop%20CI?branchName=master)](https://dev.azure.com/vagabundus/Kaninhop/_build/latest?definitionId=6&branchName=master)

### Remark:

Repository must be closed (close()/shutdown() methods) after use.

#### See also

- nl.openweb.jcr/in-memory-jcr
- org.apache.jackrabbit/jackrabbit-core
