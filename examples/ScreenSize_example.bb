Include "BlitzWinApi.bb"

Graphics3D bScreenWidth(),bScreenHeight(),32,1
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

Repeat
	Cls
	TurnEntity cube,1,1,1	
	UpdateWorld ()
	RenderWorld ()	
	Text 0,0,"window size install is "+bScreenWidth()+"x"+bScreenHeight()
	Flip ()
Until KeyHit (1)