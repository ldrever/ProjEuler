# 2024-09-20
Main thing learnt is that OF COURSE my two int functions in Problem0001.java need to be STATIC. They could only NOT be static if they were instance-specific. Put it this way - main is static, so of course anything it CALLS can't then be instance-specific.

# 2024-09-22
ArrayList<Long> not ArrayList<long>
Don't need import statements for classes in the same package
array.length, not array.length()
leading zeroes aren't allowed in integer literals
brackets work in making string-appends evaluate integer bits before including them
SEEMS, in file-reading, as though thepresence or absence of a final return character/final empty line... makes NO difference
^D DELETES A LINE IN ECLIPSE!

# 2024-09-25
^/ COMMENTS IT OUT!
https://en.wikipedia.org/wiki/Atan2
setters beat public members because one should generally do some sanitizing, and invoke eg throw new IllegalArgumentException();
a triangle's INCENTRE is the midpoint of the internal circle tangent to all three sides, which is ALSO where all three angle-bisectors meet; it's NOT on the Euler line, unlike the centroid (centre of gravity/intersection of SIDE bisectors), circumcentre(circle including all three vertices), or the orthocentre(intersection of all three "altitudes", ie vertex-to-perpendicular)

essential in vba:
CHANGE SYSTEM SOUNDS
EXCLAMATION
and also OPTIONS > NO auto syntax check YES variable declaration
and also VIEW / TOOLBARS / CUSTOMIZE / COMMANDS / EDIT / comment and uncomment

NOT TO XYZ!!!


print thisdocument.Bookmarks.Count
ActiveDocument.Bookmarks.Add Name:="Workings", Range:=Selection.Range

A word table cell's .Range.Text property is generally the visible word, followed by Chr(13) then Chr(7) - CR then BELL!