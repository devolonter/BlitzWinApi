Include "BlitzWinApi.bb"

Graphics3D 640,480,32,2
SetBuffer BackBuffer ()

region = bCreateRectRegion (640/2-45, 480/2-25, bGetWindowWidth()-640/2+45, bGetWindowHeight()-480/2+45)
bSetWindowRegion (region)

Cls

image = LoadImage ("bwalogo.jpg")
DrawImage image,640/2-50,480/2-50
Flip ()


Delay 5000

FreeImage ImageBuffer
bFreeWindowAlpha ()
EndGraphics 

Graphics3D 640,480,32,1
SetBuffer BackBuffer ()

region = bCreateRectRegion (0, 0, bGetWindowWidth(), bGetWindowHeight())
bSetWindowRegion (region)

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()


Repeat
	Cls
	TurnEntity cube,1,1,1	
	UpdateWorld ()
	RenderWorld ()	
	Flip ()
Until KeyHit (1)