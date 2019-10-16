package cc.gl.cameracode.bean

data class DetectInfo(
    val face_list: List<Face>,
    val face_num: Int
)

data class Face(
    val age: Int,
    val angle: Angle,
    val beauty: Double,
    val emotion: Emotion,
    val expression: Expression,
    val eye_status: EyeStatus,
    val face_probability: Double,
    val face_shape: FaceShape,
    val face_token: String,
    val face_type: FaceType,
    val gender: Gender,
    val glasses: Glasses,
    val landmark: List<Landmark>,
    val landmark150: Landmark150,
    val landmark72: List<Landmark72>,
    val location: Location,
    val quality: Quality,
    val race: Race
)

data class Expression(
    val probability: Double,
    val type: String
)

data class Emotion(
    val probability: Double,
    val type: String
)

data class Landmark72(
    val x: Double,
    val y: Double
)

data class Quality(
    val blur: Double,
    val completeness: Int,
    val illumination: Double,
    val occlusion: Occlusion
)

data class Occlusion(
    val chin_contour: Double,
    val left_cheek: Double,
    val left_eye: Double,
    val mouth: Double,
    val nose: Double,
    val right_cheek: Double,
    val right_eye: Double
)

data class FaceShape(
    val probability: Double,
    val type: String
)

data class Landmark150(
    val cheek_left_1: CheekLeft1,
    val cheek_left_10: CheekLeft10,
    val cheek_left_11: CheekLeft11,
    val cheek_left_2: CheekLeft2,
    val cheek_left_3: CheekLeft3,
    val cheek_left_4: CheekLeft4,
    val cheek_left_5: CheekLeft5,
    val cheek_left_6: CheekLeft6,
    val cheek_left_7: CheekLeft7,
    val cheek_left_8: CheekLeft8,
    val cheek_left_9: CheekLeft9,
    val cheek_right_1: CheekRight1,
    val cheek_right_10: CheekRight10,
    val cheek_right_11: CheekRight11,
    val cheek_right_2: CheekRight2,
    val cheek_right_3: CheekRight3,
    val cheek_right_4: CheekRight4,
    val cheek_right_5: CheekRight5,
    val cheek_right_6: CheekRight6,
    val cheek_right_7: CheekRight7,
    val cheek_right_8: CheekRight8,
    val cheek_right_9: CheekRight9,
    val chin_1: Chin1,
    val chin_2: Chin2,
    val chin_3: Chin3,
    val eye_left_corner_left: EyeLeftCornerLeft,
    val eye_left_corner_right: EyeLeftCornerRight,
    val eye_left_eyeball_center: EyeLeftEyeballCenter,
    val eye_left_eyeball_left: EyeLeftEyeballLeft,
    val eye_left_eyeball_right: EyeLeftEyeballRight,
    val eye_left_eyelid_lower_1: EyeLeftEyelidLower1,
    val eye_left_eyelid_lower_2: EyeLeftEyelidLower2,
    val eye_left_eyelid_lower_3: EyeLeftEyelidLower3,
    val eye_left_eyelid_lower_4: EyeLeftEyelidLower4,
    val eye_left_eyelid_lower_5: EyeLeftEyelidLower5,
    val eye_left_eyelid_lower_6: EyeLeftEyelidLower6,
    val eye_left_eyelid_lower_7: EyeLeftEyelidLower7,
    val eye_left_eyelid_upper_1: EyeLeftEyelidUpper1,
    val eye_left_eyelid_upper_2: EyeLeftEyelidUpper2,
    val eye_left_eyelid_upper_3: EyeLeftEyelidUpper3,
    val eye_left_eyelid_upper_4: EyeLeftEyelidUpper4,
    val eye_left_eyelid_upper_5: EyeLeftEyelidUpper5,
    val eye_left_eyelid_upper_6: EyeLeftEyelidUpper6,
    val eye_left_eyelid_upper_7: EyeLeftEyelidUpper7,
    val eye_right_corner_left: EyeRightCornerLeft,
    val eye_right_corner_right: EyeRightCornerRight,
    val eye_right_eyeball_center: EyeRightEyeballCenter,
    val eye_right_eyeball_left: EyeRightEyeballLeft,
    val eye_right_eyeball_right: EyeRightEyeballRight,
    val eye_right_eyelid_lower_1: EyeRightEyelidLower1,
    val eye_right_eyelid_lower_2: EyeRightEyelidLower2,
    val eye_right_eyelid_lower_3: EyeRightEyelidLower3,
    val eye_right_eyelid_lower_4: EyeRightEyelidLower4,
    val eye_right_eyelid_lower_5: EyeRightEyelidLower5,
    val eye_right_eyelid_lower_6: EyeRightEyelidLower6,
    val eye_right_eyelid_lower_7: EyeRightEyelidLower7,
    val eye_right_eyelid_upper_1: EyeRightEyelidUpper1,
    val eye_right_eyelid_upper_2: EyeRightEyelidUpper2,
    val eye_right_eyelid_upper_3: EyeRightEyelidUpper3,
    val eye_right_eyelid_upper_4: EyeRightEyelidUpper4,
    val eye_right_eyelid_upper_5: EyeRightEyelidUpper5,
    val eye_right_eyelid_upper_6: EyeRightEyelidUpper6,
    val eye_right_eyelid_upper_7: EyeRightEyelidUpper7,
    val eyebrow_left_corner_left: EyebrowLeftCornerLeft,
    val eyebrow_left_corner_right: EyebrowLeftCornerRight,
    val eyebrow_left_lower_1: EyebrowLeftLower1,
    val eyebrow_left_lower_2: EyebrowLeftLower2,
    val eyebrow_left_lower_3: EyebrowLeftLower3,
    val eyebrow_left_upper_1: EyebrowLeftUpper1,
    val eyebrow_left_upper_2: EyebrowLeftUpper2,
    val eyebrow_left_upper_3: EyebrowLeftUpper3,
    val eyebrow_left_upper_4: EyebrowLeftUpper4,
    val eyebrow_left_upper_5: EyebrowLeftUpper5,
    val eyebrow_right_corner_left: EyebrowRightCornerLeft,
    val eyebrow_right_corner_right: EyebrowRightCornerRight,
    val eyebrow_right_lower_1: EyebrowRightLower1,
    val eyebrow_right_lower_2: EyebrowRightLower2,
    val eyebrow_right_lower_3: EyebrowRightLower3,
    val eyebrow_right_upper_1: EyebrowRightUpper1,
    val eyebrow_right_upper_2: EyebrowRightUpper2,
    val eyebrow_right_upper_3: EyebrowRightUpper3,
    val eyebrow_right_upper_4: EyebrowRightUpper4,
    val eyebrow_right_upper_5: EyebrowRightUpper5,
    val mouth_corner_left_inner: MouthCornerLeftInner,
    val mouth_corner_left_outer: MouthCornerLeftOuter,
    val mouth_corner_right_inner: MouthCornerRightInner,
    val mouth_corner_right_outer: MouthCornerRightOuter,
    val mouth_lip_lower_inner_1: MouthLipLowerInner1,
    val mouth_lip_lower_inner_10: MouthLipLowerInner10,
    val mouth_lip_lower_inner_11: MouthLipLowerInner11,
    val mouth_lip_lower_inner_2: MouthLipLowerInner2,
    val mouth_lip_lower_inner_3: MouthLipLowerInner3,
    val mouth_lip_lower_inner_4: MouthLipLowerInner4,
    val mouth_lip_lower_inner_5: MouthLipLowerInner5,
    val mouth_lip_lower_inner_6: MouthLipLowerInner6,
    val mouth_lip_lower_inner_7: MouthLipLowerInner7,
    val mouth_lip_lower_inner_8: MouthLipLowerInner8,
    val mouth_lip_lower_inner_9: MouthLipLowerInner9,
    val mouth_lip_lower_outer_1: MouthLipLowerOuter1,
    val mouth_lip_lower_outer_10: MouthLipLowerOuter10,
    val mouth_lip_lower_outer_11: MouthLipLowerOuter11,
    val mouth_lip_lower_outer_2: MouthLipLowerOuter2,
    val mouth_lip_lower_outer_3: MouthLipLowerOuter3,
    val mouth_lip_lower_outer_4: MouthLipLowerOuter4,
    val mouth_lip_lower_outer_5: MouthLipLowerOuter5,
    val mouth_lip_lower_outer_6: MouthLipLowerOuter6,
    val mouth_lip_lower_outer_7: MouthLipLowerOuter7,
    val mouth_lip_lower_outer_8: MouthLipLowerOuter8,
    val mouth_lip_lower_outer_9: MouthLipLowerOuter9,
    val mouth_lip_upper_inner_1: MouthLipUpperInner1,
    val mouth_lip_upper_inner_10: MouthLipUpperInner10,
    val mouth_lip_upper_inner_11: MouthLipUpperInner11,
    val mouth_lip_upper_inner_2: MouthLipUpperInner2,
    val mouth_lip_upper_inner_3: MouthLipUpperInner3,
    val mouth_lip_upper_inner_4: MouthLipUpperInner4,
    val mouth_lip_upper_inner_5: MouthLipUpperInner5,
    val mouth_lip_upper_inner_6: MouthLipUpperInner6,
    val mouth_lip_upper_inner_7: MouthLipUpperInner7,
    val mouth_lip_upper_inner_8: MouthLipUpperInner8,
    val mouth_lip_upper_inner_9: MouthLipUpperInner9,
    val mouth_lip_upper_outer_1: MouthLipUpperOuter1,
    val mouth_lip_upper_outer_10: MouthLipUpperOuter10,
    val mouth_lip_upper_outer_11: MouthLipUpperOuter11,
    val mouth_lip_upper_outer_2: MouthLipUpperOuter2,
    val mouth_lip_upper_outer_3: MouthLipUpperOuter3,
    val mouth_lip_upper_outer_4: MouthLipUpperOuter4,
    val mouth_lip_upper_outer_5: MouthLipUpperOuter5,
    val mouth_lip_upper_outer_6: MouthLipUpperOuter6,
    val mouth_lip_upper_outer_7: MouthLipUpperOuter7,
    val mouth_lip_upper_outer_8: MouthLipUpperOuter8,
    val mouth_lip_upper_outer_9: MouthLipUpperOuter9,
    val nose_bridge_1: NoseBridge1,
    val nose_bridge_2: NoseBridge2,
    val nose_bridge_3: NoseBridge3,
    val nose_left_contour_1: NoseLeftContour1,
    val nose_left_contour_2: NoseLeftContour2,
    val nose_left_contour_3: NoseLeftContour3,
    val nose_left_contour_4: NoseLeftContour4,
    val nose_left_contour_5: NoseLeftContour5,
    val nose_left_contour_6: NoseLeftContour6,
    val nose_left_contour_7: NoseLeftContour7,
    val nose_middle_contour: NoseMiddleContour,
    val nose_right_contour_1: NoseRightContour1,
    val nose_right_contour_2: NoseRightContour2,
    val nose_right_contour_3: NoseRightContour3,
    val nose_right_contour_4: NoseRightContour4,
    val nose_right_contour_5: NoseRightContour5,
    val nose_right_contour_6: NoseRightContour6,
    val nose_right_contour_7: NoseRightContour7,
    val nose_tip: NoseTip
)

data class MouthLipUpperOuter8(
    val x: Double,
    val y: Double
)

data class CheekLeft11(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter6(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter10(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter3(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner5(
    val x: Double,
    val y: Double
)

data class NoseLeftContour6(
    val x: Double,
    val y: Double
)

data class CheekLeft9(
    val x: Double,
    val y: Double
)

data class CheekRight4(
    val x: Double,
    val y: Double
)

data class CheekLeft8(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner11(
    val x: Double,
    val y: Double
)

data class CheekRight2(
    val x: Double,
    val y: Double
)

data class NoseLeftContour1(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper5(
    val x: Double,
    val y: Double
)

data class EyebrowLeftUpper5(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower1(
    val x: Double,
    val y: Double
)

data class EyebrowRightUpper4(
    val x: Double,
    val y: Double
)

data class EyebrowLeftUpper1(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter2(
    val x: Double,
    val y: Double
)

data class NoseMiddleContour(
    val x: Double,
    val y: Double
)

data class EyebrowLeftCornerLeft(
    val x: Double,
    val y: Double
)

data class EyebrowRightUpper2(
    val x: Double,
    val y: Double
)

data class EyeLeftCornerRight(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter7(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower2(
    val x: Double,
    val y: Double
)

data class EyebrowLeftUpper4(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner6(
    val x: Double,
    val y: Double
)

data class Chin2(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper6(
    val x: Double,
    val y: Double
)

data class Chin3(
    val x: Double,
    val y: Double
)

data class CheekLeft7(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower4(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter10(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner7(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper3(
    val x: Double,
    val y: Double
)

data class NoseRightContour6(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower6(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper1(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner8(
    val x: Double,
    val y: Double
)

data class CheekLeft1(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower3(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower2(
    val x: Double,
    val y: Double
)

data class EyebrowRightLower3(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper2(
    val x: Double,
    val y: Double
)

data class CheekLeft10(
    val x: Double,
    val y: Double
)

data class EyebrowLeftUpper3(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner9(
    val x: Double,
    val y: Double
)

data class EyeRightEyeballRight(
    val x: Double,
    val y: Double
)

data class EyebrowRightLower2(
    val x: Double,
    val y: Double
)

data class MouthCornerRightOuter(
    val x: Double,
    val y: Double
)

data class CheekRight1(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner8(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner2(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter11(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner1(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter9(
    val x: Double,
    val y: Double
)

data class CheekRight10(
    val x: Double,
    val y: Double
)

data class NoseLeftContour3(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter5(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter5(
    val x: Double,
    val y: Double
)

data class CheekRight9(
    val x: Double,
    val y: Double
)

data class CheekLeft6(
    val x: Double,
    val y: Double
)

data class CheekLeft3(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner5(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner6(
    val x: Double,
    val y: Double
)

data class EyebrowLeftUpper2(
    val x: Double,
    val y: Double
)

data class Chin1(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter8(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower6(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner4(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper3(
    val x: Double,
    val y: Double
)

data class CheekRight8(
    val x: Double,
    val y: Double
)

data class EyebrowRightCornerLeft(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter6(
    val x: Double,
    val y: Double
)

data class CheekRight3(
    val x: Double,
    val y: Double
)

data class NoseRightContour4(
    val x: Double,
    val y: Double
)

data class EyebrowLeftCornerRight(
    val x: Double,
    val y: Double
)

data class MouthCornerLeftOuter(
    val x: Double,
    val y: Double
)

data class NoseLeftContour5(
    val x: Double,
    val y: Double
)

data class MouthCornerRightInner(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter1(
    val x: Double,
    val y: Double
)

data class NoseLeftContour2(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper7(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower4(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper7(
    val x: Double,
    val y: Double
)

data class NoseRightContour3(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower1(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower5(
    val x: Double,
    val y: Double
)

data class EyeLeftEyeballCenter(
    val x: Double,
    val y: Double
)

data class NoseLeftContour4(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter3(
    val x: Double,
    val y: Double
)

data class CheekRight6(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter2(
    val x: Double,
    val y: Double
)

data class EyebrowRightCornerRight(
    val x: Double,
    val y: Double
)

data class NoseLeftContour7(
    val x: Double,
    val y: Double
)

data class EyeLeftEyeballRight(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner1(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper6(
    val x: Double,
    val y: Double
)

data class CheekLeft2(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner9(
    val x: Double,
    val y: Double
)

data class NoseRightContour5(
    val x: Double,
    val y: Double
)

data class EyebrowRightUpper5(
    val x: Double,
    val y: Double
)

data class NoseBridge3(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner2(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner3(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper5(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter1(
    val x: Double,
    val y: Double
)

data class EyeLeftCornerLeft(
    val x: Double,
    val y: Double
)

data class EyebrowLeftLower2(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner7(
    val x: Double,
    val y: Double
)

data class MouthCornerLeftInner(
    val x: Double,
    val y: Double
)

data class EyebrowRightUpper1(
    val x: Double,
    val y: Double
)

data class EyeLeftEyeballLeft(
    val x: Double,
    val y: Double
)

data class EyebrowRightUpper3(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidUpper4(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter11(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter9(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner10(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter7(
    val x: Double,
    val y: Double
)

data class CheekRight5(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper2(
    val x: Double,
    val y: Double
)

data class CheekRight7(
    val x: Double,
    val y: Double
)

data class CheekLeft5(
    val x: Double,
    val y: Double
)

data class NoseTip(
    val x: Double,
    val y: Double
)

data class EyeRightCornerLeft(
    val x: Double,
    val y: Double
)

data class EyebrowLeftLower1(
    val x: Double,
    val y: Double
)

data class MouthLipLowerOuter4(
    val x: Double,
    val y: Double
)

data class MouthLipUpperOuter4(
    val x: Double,
    val y: Double
)

data class MouthLipUpperInner11(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower3(
    val x: Double,
    val y: Double
)

data class NoseBridge2(
    val x: Double,
    val y: Double
)

data class NoseBridge1(
    val x: Double,
    val y: Double
)

data class NoseRightContour1(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower5(
    val x: Double,
    val y: Double
)

data class EyeRightCornerRight(
    val x: Double,
    val y: Double
)

data class EyebrowLeftLower3(
    val x: Double,
    val y: Double
)

data class NoseRightContour2(
    val x: Double,
    val y: Double
)

data class EyeRightEyeballLeft(
    val x: Double,
    val y: Double
)

data class EyeRightEyelidLower7(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper1(
    val x: Double,
    val y: Double
)

data class CheekRight11(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidUpper4(
    val x: Double,
    val y: Double
)

data class EyeRightEyeballCenter(
    val x: Double,
    val y: Double
)

data class EyeLeftEyelidLower7(
    val x: Double,
    val y: Double
)

data class EyebrowRightLower1(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner3(
    val x: Double,
    val y: Double
)

data class CheekLeft4(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner10(
    val x: Double,
    val y: Double
)

data class MouthLipLowerInner4(
    val x: Double,
    val y: Double
)

data class NoseRightContour7(
    val x: Double,
    val y: Double
)

data class Gender(
    val probability: Double,
    val type: String
)

data class Angle(
    val pitch: Double,
    val roll: Double,
    val yaw: Double
)

data class Landmark(
    val x: Double,
    val y: Double
)

data class Location(
    val height: Double,
    val left: Double,
    val rotation: Int,
    val top: Double,
    val width: Double
)

data class Race(
    val probability: Double,
    val type: String
)

data class FaceType(
    val probability: Double,
    val type: String
)

data class Glasses(
    val probability: Double,
    val type: String
)

data class EyeStatus(
    val left_eye: Double,
    val right_eye: Double
)