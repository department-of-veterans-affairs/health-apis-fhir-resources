# us-core-r4-deidentifier

All deidentifers for the us-core-r4 resources are housed here.

For general rules on deidentification, see the sites below:
- https://www.hhs.gov/hipaa/for-professionals/privacy/special-topics/de-identification/index.html
- https://privacyruleandresearch.nih.gov/pr_08.asp
- https://www.law.cornell.edu/cfr/text/45/164.514
- https://www.hhs.gov/sites/default/files/ocr/privacy/hipaa/understanding/coveredentities/minimumnecessary.pdf

## Deidentification Rules Per Resource
- [Patient](#patient-deidentification)


## Patient Deidentification Rules

### Remove:
- `.identifier[]`
- `.telecom[]`
- `.address[]`
- `.photo[]`
- `.contact[]`

### Synthesize:
- `.id`
- `.name[]`
- `.birthDate`
- `.deceasedDateTime`

### Swap:
- `.multipleBirthInteger` ⮕ `.multipleBirthBoolean` 
