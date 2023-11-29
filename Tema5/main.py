import Qmap
import state


def main():
    q = Qmap.Qmap()

    # for r in q.qmap:
    #     print(r)
    s = state.State(i=3, j=0)
    q.learn(s)
    for r in q.qmap:
        print(r)