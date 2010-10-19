
; запускаем вместе Sendmessage_server_example.bb и Sendmessage_cleint_example.bb

; на сервере жмем влево вправо и смотрим что произошло с клиентом :)
Include "blitzwinapi.bb"
AppTitle  "client"
Graphics3D 640,480,32,2
SetBuffer BackBuffer ()
cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()
Repeat
	Cls
	If bGetEvent() = 666  Then
		TranslateEntity cube, -0.1,0,0
	End If

	If bGetEvent() = 665  Then
		TranslateEntity cube, 0.1,0,0
	End If

	TurnEntity cube,1,1,1
	UpdateWorld ()
	RenderWorld ()
	Flip()
Until KeyHit (1)


















































