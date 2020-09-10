## Deidentification Core Libraries

The deidentification libraries provide a framework for the deidentification of Personally Identifiable Information(**PII**).
These transformations are all **one-way**, and **irreversible**, however they are **repeatable**.

#### Synthetic Data
The SyntheticData interface defines the set of PII data-types that deidentification currently supports. These are:

- Date
- Datetime
- Names (first + last)

#### Rules
**BirthDate** and **BirthDatetime** \
`year = truncated to age 90 (i.e. ages greater than 90 y.o. become 90 y.o.)`\
`month = 1st month of the year`\
`day = first day of the month`\
`hour = 12:00`\
`min = 12:34`\
`second = 12:34:56`\
`zone = UTC`

Example:\
`1920-09-29T11:11:11Z` becomes `1930-01-01T12:34:56Z` 

**Name**\
Names deidentification requires a seed, typically an icn, for synthesis.

Example:\
`1234` becomes `Aaron Blake`