package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;

public class Wheel {

    static double conversionFactor = ((2 * Math.PI) / 3.3);

    private DcMotor speedMotor;
    private CRServo angleMotor;

    private AnalogInput encoder;

    private PID pid = new PID(PID.Type.move);

    public Wheel(DcMotor speed, CRServo angle, AnalogInput enc) {
        speedMotor = speed;
        angleMotor = angle;
        encoder = enc;
    }

    public void drive(double speed, double angle) {
        double currentAngle = encoder.getVoltage() * conversionFactor;

        double error = currentAngle - angle;

        speedMotor.setPower (speed);

        pid.pid(error);

        angleMotor.setPower (pid.pidout);

//        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
//
//        if (setpoint < 0) {
//            setpoint = MAX_VOLTS + setpoint;
//        }
//        if (setpoint > MAX_VOLTS) {
//            setpoint = setpoint - MAX_VOLTS;
//        }

//        pidController.setSetpoint (setpoint)
    }


}
